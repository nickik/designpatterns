(ns designpatterns.strategy.ente
  (:use designpatterns.core))

(defmulti fligen class)
(defmulti quacken class)

(defprotocol PEnte
  (anzeigen [this]))

(defmacro CreatEnte [syb & args]
  `(defrecord ~syb ~(into ['show] args)
     PEnte
     (anzeigen [this] (:anzeigen this))))

(CreatEnte Ente)
(CreatEnte MoorEnte)
(CreatEnte GummiEnte)
(CreatEnte PekingEnte)

(defmethod fliegen Ente [this]
           (normalflug))
(defmethod quaken Ente [this]
           (quaken))
(defmethod fliegen MoorEnte [this]
           (fliegtnicht))
(defmethod quacken MoorEnte [this]
           (quaken))
(defmethod fliegen GummiEnte [this]
           (fliegtnicht))
(defmethod quacken GummiEnte [this]
           (quietschen))


(defn normalflug []
  "normaler Flug")
(defn fliegtnicht []
  "fligt nicht")

(defn quaken []
  "quaken")
(defn quietschen []
  "quitsch!!")
(defn stumm []
  "<<stille>>")
