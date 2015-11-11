(ns big-time.util.time)

(defn time-str [int]
  (if (= (count (str int)) 1)
    (str "0" int)
    (str int)))

(defn- str->int [str]
  (js/parseInt str 10))

(defn- date->vector [date]
  [(time-str (.getHours date))
   (time-str (.getMinutes date))
   (time-str (.getSeconds date))])

(defn vector->seconds [time-vector]
  (let [[hours minutes seconds] (map str->int time-vector)]
    (+ (* hours 60 60) (* minutes 60) seconds)))

(defn seconds->vector [seconds]
  (date->vector (js/Date. (* seconds 1000))))

(defn now []
  (js/Date.))

(defn- duplicate [time]
  (js/Date. time))

(defn- add-seconds [time seconds]
  (let [new-time (duplicate time)]
    (.setSeconds new-time (+ (.getSeconds time) seconds))
    new-time))

(defn- seconds-until [time]
  (/ (- (.getTime time) (.getTime (now))) 1000))

(defn seconds-left [start-time duration]
  (seconds-until (add-seconds start-time duration)))

(defn current-time-vector []
  (date->vector (now)))