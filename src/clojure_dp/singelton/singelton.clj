(ns clojure-db.singelton.singelton
  (:use clojure.contrib.singleton))

(defn getConnection []
  "This is a connection")

(def getSingelton (global-singleton getConnection))

