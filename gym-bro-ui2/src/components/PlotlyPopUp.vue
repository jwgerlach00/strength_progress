<template>
  <div id="plot-container" class="plot-container"></div>
</template>

<script setup>
import { ref, defineProps, watch, nextTick } from 'vue'
import Plotly from 'plotly.js-dist'

const props = defineProps({
  plotData: {
    type: Array,
    default: () => [],
    required: true
  },
  plotLayout: {
    type: Object,
    required: true
  }
})

const plotlyConfig = {
  editable: true,
  displaylogo: false,
  toImageButtonOptions: {
    format: 'svg',
    filename: props.downloadKey
  }
}

// Plotly.react('plot-container', plotData, props.plotLayout, plotlyConfig)
// Plotly.newPlot('plot-container', plotData, props.plotLayout)

const plotData = ref({})

watch(() => props.plotData, () => {
  nextTick(() => {
    Plotly.react('plot-container', plotData, props.plotLayout, plotlyConfig)
  })
}, { immediate: true })

plotData.value = {
  x: [1, 2, 3, 4],
  y: [1, 1, 1, 1],
  type: 'scatter'
}
</script>
