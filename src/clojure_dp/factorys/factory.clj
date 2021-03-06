(ns clojure-dp.factorys.factory)

(defrecord Pizza [name teig sosse belag backen schneiden verpackung])
(defn getPizza
  ([name teig sosse belag]
     (Pizza. name teig sosse belag
             "Bache die Pizza für 25min bei 350 Grad"
             "Pizza in stücke schneiden"
             "Pizza in die Schachtel packen"))
  ([name teig sosse belag backen schneiden verpackung] 
     (Pizza. name teig sosse belag backen schneiden verpackung)))
(defmulti BestellePizza  (fn [Filial Typ] [Filial Typ]))
(defmethod BestellePizza ["Berlin" "Vegetarisch"] [F T]
           (getPizza "Vegetarische Pizza Berliner Art"
                     "Teig mit fester Kruste"
                     "Marinara-Soße"
                     ["Geriebener Parmesan" "Knoblauch" "Zwiebeln" "Pilze" "Rote Paprika"]))
(defmethod BestellePizza ["Berlin" "Krabben"] [F T]
           (getPizza "Krabben-Pizza Berliner Art"
                     "fester Kruste"
                     "Marinara-Soße"
                     ["Geriebener Parmesan" "Knoblauch" "Frische Nordsee-Krabben"]))
(defmethod BestellePizza ["Berlin" "Thunfisch"] [F T]
           (getPizza "Thunfisch-Pizza Berliner Art"
                     "Teig mit fester Kruste"
                     "Marinara-Soße"
                     ["Geriebener Parmesan" "Knoblauch" "Zwiebeln"]))
(defmethod BestellePizza ["Berlin" "Salami"] [F T]
           (getPizza "Salami-Pizza Berliner Art"
                     "fester Kruste"
                     "Marinara-Soße"
                     ["Geriebener Parmesan" "Salami in Scheiben"]))
(defmethod BestellePizza ["München" "Vegetarisch"] [F T]
           (getPizza "Deftige vegetarische Pizza Münchener Art"
                     "Teig mit extra dicker Kruste"
                     "Tomatensoße"
                     ["Mozzarella" "Schwarze Oliven" "Spinat" "Aubergine"]))
(defmethod BestellePizza ["München" "Krabben"] [F T]
           (getPizza "Krabben-Pizza Münchener Art"
                     "Teig mit extra dicker Kruste"
                     "Tomatensoße";
                     ["Mozzarella" "Gefrorene Krabben" "Oliven"]))

(defmethod BestellePizza ["München" "Thunfisch"] [F T]
              (getPizza	"Thunfisch-Pizza Münchener Art"
                        "Teig mit extra dicker Kruste";
                        "Tomatensoße";
                        ["Mozzarella" "Schwarze Oliven"
                         "Thunfisch" "Kapern"]))
(defmethod BestellePizza ["München" "Salami"] [F T]
           (getPizza "Deftige Salami-Pizza im Münchener Stil"
                     "Teig mit extra dicker Kruste"
                     "Tomatensoße"
                     ["Salami""Mozzarella"]))
(defmethod BestellePizza [:NoFiliale :NoPizza] [F T]
           (println "Keine Gültige Pizza"))
(defn Vorbereitung [pizza]
  (apply str "Vorbereitung ...\n"
             "Teig knetten \n"
             "Sosse hinzufügen \n"
             "belegen mit " (interpose " "(:belag pizza))))
(defn MachEine [pizza] (str "Mach eine: " (:name pizza)))
(defn bestelle [F T]
  (let [pizza (BestellePizza F T)]
    {:Zubereitung (str (MachEine pizza) "\n"
                       (Vorbereitung pizza) "\n"
                       (:backen pizza) "\n"
                       (:schneiden pizza) "\n"
                       (:verpackung pizza) "\n")
     :pizza pizza}))
(defn Pizza-Name [F T]
  (:name :pizza (BestellePizza F T)))

;;Testdrive

(defn TestFunction []
  (println "Ethan bestellte eine " (Pizza-Name "Berlin" "Salami"))
  (println "Joel bestellte eine " (Pizza-Name "Berlin" "Salami"))
  (println "Ethan bestellte eine " (Pizza-Name "Berlin" "Salami"))
  (println "Joel bestellte eine " (Pizza-Name "Berlin" "Salami"))

  (println "Ethan bestellte eine " (Pizza-Name "Berlin" "Salami"))
  (println "Joel bestellte eine " (Pizza-Name "Berlin" "Salami"))
  (println "Ethan bestellte eine " (Pizza-Name "Berlin" "Salami"))
  (println "Joel bestellte eine " (Pizza-Name "Berlin" "Salami")))
                  
