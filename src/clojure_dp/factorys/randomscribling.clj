(ns clojure.factorys.factory)

(defprotocol PPizzeria
  (erstellePizza [this PizzaTyp Factory]))

(defrecord Fabrik [teig sosse käse salami gemüse thunfisch krabben])
(def BerlinFabrik (Fabrik.
                   ["dünn" "knusprig"]
                   "würzig"
                   "wenig käse"
                   "spanischer Salami"
                   "scheiss auf gemüse"
                   "normaler thunfisch"
                   "nordseekraben"))

(defrecord PizzeriaBerlin []
  PPizzeria
  (erstellePizza [filiale  PizzaTyp Factory]
                 (condp PizzaTyp
                     "Salami" :>> (SalamiPizza Factory)
                     "Schinken" :>> (SchinkenPizza Factory)
                     "Krabben" :>> (KrabbenPizza Factory)
                     "Thunfisch" :>> (ThunfischPizza Factory))))

(defn StandardSchneiden []
  (println "Schneide die Pizza diagonal in schtücke"))
(defn StandardVerpacken []
  (println "Packe die Pizza in die ofizielle Pizzeria-Schachtel"))
(defn StandardBacken []
  (println "Backe 25 Minuten bei 350"))

(defrecord Pizza [name teig sosse käse belag schneiden backen Verpacken])

(defn StandardPizza [name teig sosse käse belag]
  (Pizza. name
          teig
          sosse
          käse
          belag
          StandardSchneiden
          Standardbacken
          StandardVerpacken))

(defn PizzaFunc  [key_ func pizza]
  (if (nil? (key_ Pizza))
    (func)
    ((key_ Pizza))))

(defn Schneiden [Pizza]
  (PizzaFunc :schneiden StandardSchneiden Pizza))

(defn Backen [Pizza]
  (PizzaFunc :backen StandardBacken Pizza))

(defn Verpacken [Pizza]
  (PizzaFunc :verpacken StandardVerpacken Pizza))

(defn bestellePizza [filiale PizzaTyp]
  (erstellePizza filiale PizzaTyp))

(defn SalamiPizza [Factory]
  (Pizza. "SalamiPizza"
          (:teig Factory)
          (:sosse Factory)
          (:käse Factory)
          [(:salami Factory)]))
