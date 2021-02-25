(ns analog-clock-face.events
  (:require
   [re-frame.core :as re-frame]
   [analog-clock-face.db :as db]
   [day8.re-frame.tracing :refer-macros [fn-traced]]
   ))

(re-frame/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
            db/default-db))

(re-frame/reg-event-db
 :time-color-change
 (fn [db [_ new-color-value]]
   (assoc db :time-color new-color-value)))

(re-frame/reg-event-db
 :timer
 (fn [db [_ new-time]]
   (assoc db :time new-time)))




