(ns clojure4j.Core
  (:gen-class
     :methods
       [#^{:static true} [hello [] String]]
       [#^{:static true} [map [AFn IPersistentCollection] ISeq]]))

(defn -hello []
  "Hello clojure")

(defn -map [f coll]
  (map f coll))

;; (defn -apply [f coll]
;;   (apply f coll))
;; (reduce)
;; (defn -filter [pred coll]
;;   (filter pred coll))

;; (defn -threadFirst [x form]
;;   (-> x form))

;; (defn -threadLast [x form]
;;   (->> x form))

;; (def d (Double. 4.0))
;; (def r (proxy [java.lang.Runnable] [] (run [] (println "foo"))))

;; (.run r)

;; (->>
;;  (-> [] (conj 1 2 3))
;;  (filter odd?)
;;  (map #(* % 2)))

(type (->>
       [1 2 3]
       (filter odd?)
       (map #(* % 2))))
