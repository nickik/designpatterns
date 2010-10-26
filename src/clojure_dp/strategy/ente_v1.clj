(ns designpatterns.strategy.ente
  (:use designpatterns.core))

;Ein protocol/interface f�r die Ente einfach weil es guter Stil ist
;(auch wenn nicht forhanden im Buch)
(defprotocol PEnte 
	(anzeigen [this])
	(fliegen [this])
	(t�nen [this]))

;Das sind die "Strategie-Objecte" was in unseremfall ganz normale
                                        ;funktionen sind
;Interfaces zu erstellen finde ich hier �berfl�ssig k�nnte aber mit
;mulimethodes in �hnlichem aufwand gemacht werden
(defn fliegt_normal []
  (println "fliegt normal"))
(defn fliegt_nicht []
  (println "fliegt_nicht"))
(defn quacken []
  (println "quack"))
(defn quitschen []
  (println "quietsch"))
(defn stumm []
  (println "<<stille>>"))

;Ein macro das die beschreibung einer Ente erh�lt und die Enteklass
;daraus definiert
(defmacro defente [syb & {fliegen :fliegen
                        ger�usch :ger�usch
                        show :show
                        items :items
                        :or {fliegen fliegt_normal
                             ger�usch quacken
                             show (str syb)}}]
  `(defrecord ~syb ~(into [] items)
     PEnte
     (anzeigen [this] ~show)
     (fliegen [this] (~fliegen))
     (t�nen [this] (~ger�usch))))

(defente Ente)
(defente MoorEnte)
(defente GummiEnte {:ger�usch quitschen
                    :fliegen fliegt_nicht})
(defente LookEnte {:fliegt fliegt_nicht})



				


