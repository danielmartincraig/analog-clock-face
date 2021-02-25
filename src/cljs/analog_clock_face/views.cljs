(ns analog-clock-face.views
  (:require
   [re-frame.core :as re-frame]
   [analog-clock-face.subs :as subs]
   [clojure.string :as str]
   ))

;; "FITNESS DJ"

(defn dispatch-timer-event
  []
  (let [now (js/Date.)]
    (re-frame/dispatch [:timer now])))

(defonce do-timer (js/setInterval dispatch-timer-event 1000))

(defn color-input
  []
  [:div.color-input
   "Clock color: "
   [:input {:type "text"
            :value @(re-frame/subscribe [:time-color])
            :on-change #(re-frame/dispatch [:time-color-change (-> % .-target .-value)])}]])

(defn analog-clock-face []
  (let [msPerDay (* 86400 1000)
        time (re-frame/subscribe [:time])
        color (re-frame/subscribe [:time-color])]
    (fn []
      (let [hand-angle (-> @time
                           (.getTime)
                           (mod msPerDay)
                           (/ msPerDay)
                           (* (* 2 js/Math.PI))
                           (+ js/Math.PI))
            hand-endpoint [(* 85 (js/Math.cos hand-angle))
                           (* 85 (js/Math.sin hand-angle))]]
        [:div
         [:svg
          {:style {:width 200
                   :height 200}
           :view-box "-100 -100 200 200"}
          [:circle {:r 90
                    :style {:fill "white"
                            :stroke @color
                            :stroke-width 3}}]
          [:path {:stroke @color
                  :d (str "M 0 0 L " (clojure.string/join " " hand-endpoint))}]]]))))

  (defn main-panel []
    (let [name (re-frame/subscribe [::subs/name])]
      [:div
       [:h1 "Hello world, it is now"]
       [analog-clock-face]
       [color-input]]))

