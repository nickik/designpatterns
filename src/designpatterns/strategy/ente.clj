(ns designpatterns.strategy.ente
  (:use designpatterns.core))

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

(defn ente [anzeigen flug ton]
  {:show anzeigen :fly flug :ton ton})

(def StockEnte (ente "StockEnte" normalflug quaken))
(def MoorEnte (ente "moorente" normalflug   quaken))
(def GummiEnte (ente "gummiente" fliegtnicht quietschen))
(def LookEnte (ente "LockEnte" normalflug stumm))

(defn fliegen [Ente]
  ((:fly Ente)))
(defn doton [Ente]
  ((:ton Ente)))
(defn anzeigen [Ente]
  (println (:show Ente)))


