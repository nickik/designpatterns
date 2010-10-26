(ns designpatterns.command.command)

(defrecord Remote [on off])

(defn On [remote n]
  (get (:on remote) n))

(defn Off [remote n]
  (get (:off remote) n))

(defn remote-update [remote key arg]
  (update-in remote [key] conj arg))

(defn AddConfRemote [remote on off]
  (let  [onremote (remote-update remote :on on)]
    (remote-update onremote :off off)))

(defn GenerateRemote [on-off-map]
  (let [onvec (:on on-off-map)
        offvec (:off on-off-map)]
    (Remote. onvec offvec)))

