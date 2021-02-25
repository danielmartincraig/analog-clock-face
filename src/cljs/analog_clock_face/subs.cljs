(ns analog-clock-face.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::name
 (fn [db]
   (:name db)))

(re-frame/reg-sub
 :time
 (fn [db _]
   (:time db)))

(re-frame/reg-sub
 :time-color
 (fn [db _]
   (:time-color db)))


