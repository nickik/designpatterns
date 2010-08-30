(ns designpatterns.observer.observer)

(def WeatherData (ref {:temperature 10 :humidity 20 :barometric-presure 15}))

(defn CCDDisplay [_ _ old-state {:keys [temperature humidity]}]
	   (println (str "Current conditions: "
		temperature "F degrees and "
		humidity "% humidity")))	
(add-watch  WeatherData :CCDDisplay CCDDisplay)
