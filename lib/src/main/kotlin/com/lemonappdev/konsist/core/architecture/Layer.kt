package com.lemonappdev.konsist.core.architecture

 data class Layer(val name: String, val isDefinedBy: String) {
     // after make private?
      val isDependOnLayers = mutableListOf<Layer>()

     fun addDependentLayers(layer: Layer, vararg layers: Layer) {
         isDependOnLayers.add(layer)
         layers.forEach { isDependOnLayers.add(it) }
     }

     fun addDependentLayers(layers: List<Layer>) {
         isDependOnLayers.addAll(layers)
     }

     fun removeDependentLayers(layer: Layer, vararg layers: Layer) {
         isDependOnLayers.remove(layer)
         layers.forEach { isDependOnLayers.remove(it) }
     }

     fun clearDependentLayers(){
         isDependOnLayers.clear()
     }
 }
