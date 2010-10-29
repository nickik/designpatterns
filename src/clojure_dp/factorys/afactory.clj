(ns clojure-dp.factorys.afactory)

(defrecord Zutaten [teig sosse käse salami gemüse krabben thunfisch])

(defmulti Teig identity)
(defmethod Teig "Berlin" [filiale] "Teig mit dünner Kruste")
(defmethod Teig "München" [filiale] "Teig mit dicker Kruste")

(defmulti Sosse identity)
(defmethod Sosse "Berlin" [filiale] "Marinarasosse")
(defmethod Sosse "München" [filiale] "Tomatensosse")

(defmulti Käse identity)
(defmethod Käse "Berlin" [filiale] "Parmesan")
(defmethod Käse "München" [filiale] "Mozerlla")

(defmulti Gemüse identity)
(defmethod Gemüse "Berlin" [filiale] ["Knoblauch" "Zwiebeln" "Pilze" "Paprika"])
(defmethod Gemüse "München" [filiale] ["Schwarze Oliven" "Spinat" "Aubergine"])

(defmulti Thunfisch identity)
(defmethod Thunfisch "Berlin" [filiale] "Thunfischstücke")
(defmethod Thunfisch "München" [filiale] "Thunfischstücke")

(defmulti Salami identity)
(defmethod Salami "Berlin" [filiale] "Scharfe Spanische Salami")
(defmethod Salami "München" [filiale] "Italienische Salami")

(defmulti Krabben identity)
(defmethod Krabben "Berlin" [filiale] "frische Krabben")
(defmethod Krabben "München" [filiale] "Gefrorene Krabben")


(def PizzaNamesBerlin ["Salami-Pizza Berliner Art" "Vegetarische Pizza Berliner Art"
                       "Krabben-Pizza Berliner Art" "ThunfischPizza Berliner Art"])

(defn Zutatenfactory [ort]
  (Zutaten. (Teig ort)   (Sosse ort)   (Käse ort) (Salami ort)
            (Gemüse ort) (Krabben ort) (Thunfisch ort)))

(def ZutatenBerlin (Zutatenfactory "Berlin"))
		       
(def PizzaNamesMünchen ["Salami-Pizza Münchener Art" "Vegetarische Pizza Münchener Art"
                        "Krabben-Pizza Münchener Art" "Thunfisch-Pizza Münchener Art"])

(def ZutatenMünchen (Zutatenfactory "München"))

(defn WithStandard [& rest]
  (concat rest [:teig :sosse :käse]))

(defn getPizza [Zutaten belag]
  (apply hash-map (interleave belag (map (into {} Zutaten) belag))))

(defn StandardPizza [zutaten belag name]
  (assoc (getPizza zutaten belag) :name name))

(def SalamiPizza (WithStandard :salami))
(def VegetarischePizza (WithStandard :gemüse))
(def KrabbenPizza (WithStandard :krabben))
(def ThunfischPizza (WithStandard :gemüse :thunfisch))
(def StandardPizzas [[:salami SalamiPizza]
                     [:vegetarisch VegetarischePizza]
                     [:krabben KrabbenPizza]
                     [:thunfisch ThunfischPizza]])

(defn FilialenPizzas [Zutatenlagern Pizzalist Pizzanames]
  (apply merge (map (fn [[key pizza] name]
          {key ((partial StandardPizza Zutatenlagern) pizza name)})
        Pizzalist  Pizzanames)))

(def BerlinerPizzas (FilialenPizzas ZutatenBerlin StandardPizzas PizzaNamesBerlin))
(def MünchnerPizzas (FilialenPizzas ZutatenMünchen StandardPizzas PizzaNamesMünchen))

(def Pizzakarte (merge {:Berlin BerlinerPizzas}  {:München MünchnerPizzas}))

(defn vorbereitung [pizza]
  (println "Mache: " (:name pizza)))

(defn backen []
  (println "Backe 25 Minuten bei 350"))
 
(defn schneiden []
  (println "Schneide die Pizza diagonal in Stücke"))

(defn verpacken []
  (println "Packe die Pizza in die offizielle Pizzeria-Schachtel"))

(println "Ethan hat eine " (-> Pizzakarte :Berlin :salami :name)  " bestellt\n")
(println "Joal hat eine " (-> Pizzakarte :Berlin :vegetarisch :name) " bestellt\n")

(defn zutaten-str [pizza & keys]
  (for [key keys]
     (when-not (nil? (key pizza))
       (str (key pizza) \newline))))

(defn PizzaString [pizza]
  (str " ----- " (:name pizza) " ----- " \newline
       (apply str (zutaten-str pizza :teig :sosse :käse :salami :krabben :thunfisch :gemüse))))
