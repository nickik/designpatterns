(ns designpatterns.factorys.factory_v1)

(def BerlinerSalamiPizza (getPizza "SalamiPizza" "knusprig" "würzig" ["salami"]))
(def BerlinerProsuto (getPizza "Prosuto" "knusprig" "würzig" ["schinken"]))

(def PizzeriaBerlin {"salami" BerlinerSalamiPizza
                     "schinken" BerlinerProsuto})

(def KölnSalami (getPizza "SalamiPizza" "dick" "tomate" ["salami"]))

(def PizzeriaKöln {"salami" KölnSalami})

(defn erstellePizza [filiale PizzaType]
  (filiale PizzaType))

(defprotocol PPizza
  (vorbereitung [this])
  (Print-Pizza [this])
  (backen [this])
  (schneiden [this])
  (einpacken [this]))

(defn backen_ [] "Bache die Pizza für 25min bei 350 Grad")
(defn schneiden_  [] "Cutting the pizza into diagonal slices")
(defn einpacken_ [] "Pack the Pizza in the offical box")

(defrecord Pizza [name teig sosse belag backen schneiden einpacken]
  PPizza
  (vorbereitung [this]
                (apply str "Vorbereitung ..." \n
                       "Teig knetten" \n
                       "Sosse hinzufügen" \n
                       "belegen mit "belag ))
  (Print-Pizza [this] (print (apply str "----- " (:name this) " -----"
                                    \newline belag)))
  (backen [this] ((:backen this)))
  (schneiden [this] ((:schneiden this)))
  (einpacken [this] ((:einpacken this))))

(defn getPizza [name teig sosse belag]
  (Pizza. name teig sosse belag backen_ schneiden_ einpacken_))





