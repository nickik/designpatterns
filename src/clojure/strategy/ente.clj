(ns designpatterns.strategy.ente
  (:use designpatterns.core))

;; Zuerst defineren wir verschiedene verhalten für fliegen und quaken
(defn normalflug []
  "normaler Flug")
(defn fliegtnicht []
  "fligt nicht")

(defn quaken []
  "quak!")
(defn quietschen []
  "quitsch!!")
(defn stumm []
  "<<stille>>")

;;Die Ente funktion is im prinzip ein constructor (für eine dataen
;;nicht klassen)
(defn ente [anzeigen flug ton]
  {:show anzeigen :fly flug :ton ton})

;; Wir definieren enten indem wir der ente funktion einen namen und
;; die gewünschten verhalten übergeben
(def StockEnte (ente "StockEnte" normalflug quaken))
(def MoorEnte (ente "moorente" normalflug   quaken))
(def GummiEnte (ente "gummiente" fliegtnicht quietschen))
(def LookEnte (ente "LockEnte" normalflug stumm))

;; Dies sind die funktionen die, die verhalten ausfüheren
(defn fliegen [Ente]
  ((:fly Ente)))
(defn toenen [Ente]
  ((:ton Ente)))
(defn anzeigen [Ente]
  (println (:show Ente)))


