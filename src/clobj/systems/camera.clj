(ns clobj.systems.camera
  (:use clobj.lib.input)
  (:import (org.lwjgl.opengl GL11)))

(defn renderer [ents]
  (doseq [e ents]
    ((get-in e [:renderable :fn]) e)))

(defn move [e keyState keyValue]
  (if keyState
    (cond
      (= keyValue (key? :left))
      (GL11/glRotatef 10.0 0.0 1.0 0.0)
      (= keyValue (key? :right))
      (GL11/glRotatef -10.0 0.0 1.0 0.0)
      (= keyValue (key? :up))
      (GL11/glRotatef 10.0 1.0 0.0 0.0)
      (= keyValue (key? :down))
      (GL11/glRotatef -10.0 1.0 0.0 0.0)))) 
