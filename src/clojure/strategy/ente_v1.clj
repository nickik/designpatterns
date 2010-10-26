(ns designpatterns.strategy.ente
  (:use designpatterns.core))

;Ein protocol/interface für die Ente einfach weil es guter Stil ist
;(auch wenn nicht forhanden im Buch)
(defprotocol PEnte 
	(anzeigen [this])
	(fliegen [this])
	(tönen [this]))

;Das sind die "Strategie-Objecte" was in unseremfall ganz normale
                                        ;funktionen sind
;Interfaces zu erstellen finde ich hier überflüssig könnte aber mit
;mulimethodes in ähnlichem aufwand gemacht werden
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

;Ein macro das die beschreibung einer Ente erhält und die Enteklass
;daraus definiert
(defmacro defente [syb & {fliegen :fliegen
                        geräusch :geräusch
                        show :show
                        items :items
                        :or {fliegen fliegt_normal
                             geräusch quacken
                             show (str syb)}}]
  `(defrecord ~syb ~(into [] items)
     PEnte
     (anzeigen [this] ~show)
     (fliegen [this] (~fliegen))
     (tönen [this] (~geräusch))))

(defente Ente)
(defente MoorEnte)
(defente GummiEnte {:geräusch quitschen
                    :fliegen fliegt_nicht})
(defente LookEnte {:fliegt fliegt_nicht})



				


