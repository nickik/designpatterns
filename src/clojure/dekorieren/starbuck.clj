(ns designpatterns.dekorieren.starbuck
  (:use designpatterns.core))

(def Getränke {:hausmischung {:preis 0.89 :name "Hausmischung"}
               :dunkle-röstung {:preis 0.99 :name "Dunkle Röstung"}
               :espresso {:preis 1.99 :name "Esspresso"}
               :entkoffeiniert {:preis 1.05 :name "Entkoffeinert"}})
(def Zutaten {:heisse-milch {:preis 0.1 :name "heisse Milch"}
              :schoko {:preis 0.2 :name "Schoko"}
              :soja {:preis 0.15 :name "Soja"}
              :milchschaum {:preis 0.1 :name "Milchschaum"}})

(defn GetGetränk [Zutaten key_ reduce-fn basis  lst-zutaten]
  (reduce reduce-fn (map (fn [k] (get-in Zutaten [k key_])) lst-zutaten)))

(defn GetränkName [Getränke Zutaten basis lst-zutaten]
  (str (get-in Getränke [basis :name])
       " "
       (GetGetränk Zutaten :name
                   (fn [s1 s2] (str s1 \, " " s2))
                   basis
                   lst-zutaten)))

(defn GetränkPreis [Getränke Zutaten  basis lst-zutaten]
     (+ (get-in Getränke [basis :preis])
        (GetGetränk Zutaten :preis + basis lst-zutaten)))

(defn Getränk [Getränke Zutaten b lst]
  (if (seq lst)
    (str (GetränkName Getränke Zutaten  b lst) ": "
         (GetränkPreis Getränke Zutaten b lst) "$")
    (str (get-in Getränke [b :name]) ": " (get-in Getränke [b :preis]))))

(defn StarbuckTestOutput []
  (println (Getränk  Getränke Zutaten
                             :espresso []))
  (println (Getränk  Getränke Zutaten
                             :dunkle-röstung [:schoko :schoko :milchschaum]))
  (println (Getränk  Getränke Zutaten
                             :hausmischung [:soja :schoko :milchschaum])))

