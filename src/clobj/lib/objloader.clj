(ns clobj.lib.objloader
  (:use [clojure.string :only (split)]
        [clobj.lib.macros :only (!)]))

(defn v3 [x y z]
  {:x x
   :y y
   :z z})

(defn parseVertex [line]
  (let [line (split line #" ")]
    (v3 (read-string (get line 1))
        (read-string (get line 2))
        (read-string (get line 3)))))

(defn parseFace [line]
  (let [line (split line #" ")]
    {:v [(read-string (nth (split (nth line 1) #"\/") 0))
         (read-string (nth (split (nth line 2) #"\/") 0))
         (read-string (nth (split (nth line 3) #"\/") 0))]
     :n [(read-string (nth (split (nth line 1) #"\/") 2))
         (read-string (nth (split (nth line 2) #"\/") 2))
         (read-string (nth (split (nth line 3) #"\/") 2))]}))

(defn parser [e line]
  (let [vertices (get e :vertices)
        normals (get e :normals)
        faces (get e :faces)]
    (cond (.startsWith line "v ")
          (assoc e :vertices
                    (conj vertices (parseVertex line)))
          (.startsWith line "vn ")
          (assoc e :normals
                    (conj normals (parseVertex line)))
          (.startsWith line "f ")
          (assoc e :faces
                 (conj faces (parseFace line)))
          :else e)))

(defn loadobj [ents]
  (doseq [e ents]
    (let [model (get-in e [:obj :obj])]
      (with-open [rdr (clojure.java.io/reader model)]
        (! e :obj (reduce parser (get e :obj) (line-seq rdr)))))))
