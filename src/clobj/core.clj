(ns clobj.core
  (:use [clobj.lib.core :only (all-e add)]
        [clobj.components :only (obj renderable keyboard)]
        [clobj.renderers :only (render-obj)]
        [clobj.systems.camera :as camera]
        [clobj.lib.objloader :only (loadobj)])
  (:require [clobj.systems.input :as input])
  (:import (org.lwjgl.opengl Display DisplayMode GL11)))

(defn init []
  (let [[x y] [800 600]]
    (Display/setDisplayMode (DisplayMode. x y))
    (Display/setTitle "Breakout!")
    (Display/create)
    (GL11/glEnable GL11/GL_TEXTURE_2D)
    (GL11/glClearColor 0.0 0.0 0.0 0.0)
    (GL11/glEnable GL11/GL_BLEND)
    (GL11/glViewport 0 0 x y)
    (GL11/glMatrixMode GL11/GL_MODELVIEW)
    (GL11/glMatrixMode GL11/GL_PROJECTION)
    (GL11/glLoadIdentity)
    (GL11/glOrtho -10.0 10.0 -10.0 10.0 8.0 -8.0)
    (GL11/glMatrixMode GL11/GL_MODELVIEW)
    (GL11/glPolygonMode GL11/GL_FRONT_AND_BACK GL11/GL_LINE)
    (add (merge (renderable render-obj)
                (obj "bunny.obj")))
    (add (keyboard camera/move))
    (loadobj (all-e :obj))))


(defn finalise []
  (Display/destroy))

(defn -main
  "I don't do a whole lot."
  [& args]
  (let [g (init)]
    (println "Starting main loop...")
    (while (not (Display/isCloseRequested))
      (GL11/glClear (GL11/GL_COLOR_BUFFER_BIT))
      (input/keyboard (all-e :keyboard))
      (camera/renderer (all-e :renderable))
      (Display/update)
      (Display/sync 60))
    (finalise)))
