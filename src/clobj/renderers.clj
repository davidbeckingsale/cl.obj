(ns clobj.renderers
  (:import (org.lwjgl.opengl GL11)))

(defn render-obj [e]
  (let [vertices (get-in e [:obj :vertices])
        normals (get-in e [:obj :normals])
        faces (get-in e [:obj :faces])]
    (GL11/glBegin GL11/GL_TRIANGLES)
    (GL11/glColor3f 1.0 1.0 1.0)
    (doseq [f faces]
      (let [n-ind (:n f)
            v-ind (:v f)
            n1 (nth normals (- (nth n-ind 0) 1))
            n2 (nth normals (- (nth n-ind 1) 1))
            n3 (nth normals (- (nth n-ind 2) 1))
            v1 (nth vertices (- (nth v-ind 0) 1))
            v2 (nth vertices (- (nth v-ind 1) 1))
            v3 (nth vertices (- (nth v-ind 2) 1))]
        (GL11/glNormal3f (:x n1) (:y n1) (:z n1))
        (GL11/glVertex3f (:x v1) (:y v1) (:z v1))
        (GL11/glNormal3f (:x n2) (:y n2) (:z n2))
        (GL11/glVertex3f (:x v2) (:y v2) (:z v2))
        (GL11/glNormal3f (:x n3) (:y n3) (:z n3))
        (GL11/glVertex3f (:x v3) (:y v3) (:z v3))))
    (GL11/glEnd)))


