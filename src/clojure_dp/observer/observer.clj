(ns clojure-dp.observer.observer)

;; Allgemein: Das Observermuster ist nÃtzlich aber in FP is es kaum nötig da man versucht mutabel state zu verändern.

;;Aktuelle Anzeige
;; Hier habe ich daten die sich Ãndern
(def WeatherData (ref {:temperature 10 :humidity 20 :presure 15}))

;;Eine Watcher funktion (diese bekommen immer vier parameter. Der letzt ist der neu stat der daten. 
;;Ich schneide direkt die Temperatur und die Humidity aus und gebe diese aus.
(defn CCDDisplay [_ _ old-state {:keys [temperature humidity]}]
  (println "Current conditions: "
                  temperature "F degrees and "
                  humidity "% humidity"))

;; Als letztes faehge ich einfach die funktion zu den daten.
(add-watch  WeatherData :CCDDisplay CCDDisplay)

;;StatistikAnzeige
(def templst (atom []))
(defn MMMTemp [_ _ _ {temp :temperature}]
  (swap! templst conj temp)
  (println (str
      "Mit/Max/Min Temperatur = " (float (/ (apply + @templst) (count @templst)))
      "/" (apply max @templst) "/" (apply min @templst))))

(add-watch WeatherData :MMMTemp MMMTemp)

;;Vorhersageanzeige
(defn vorheranz [_ _ {old-p :presure} {cur-p :presure}]
  (println "Vorhersage: "(cond
    (> old-p cur-p) "Wetter wird besser"
    (< old-p cur-p) "Es wird kälter"
    (= old-p  cur-p) "Es bleibt wies ist.")))

(add-watch WeatherData :vorheranz vorheranz)
