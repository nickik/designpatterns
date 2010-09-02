(ns designpatterns.command.command)

(defprotocol PRemote)
(defrecord Remote [on-buttons off-buttons undo-button])

(defn On [remote n]
  (get (:on-buttons remote) n))

(defn Off [remote n]
  (get (:off-buttons remote) n))

(defn Undo [remote]
  ((:undo-button remote)))
