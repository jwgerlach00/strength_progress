<template>
  <div class="small-chart">
    <Line
      id="my-chart-id"
      :options="chartOptions"
      :data="chartData"
    />
  </div>
</template>

<script setup lang="ts">
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend
} from 'chart.js'
import { defineProps, computed } from 'vue'
import { Line } from 'vue-chartjs'

ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend
)

const props = defineProps({
  data: { type: Array, default: () => [] }
})

const chartData = computed(() => {
  const labels = []
  for (let i = 1; i <= props.data.length; i++) {
    labels.push(`week ${i}`)
  }
  return {
    labels: labels,
    datasets: [{
      label: 'Data One',
      backgroundColor: '#f87979',
      fill: false,
      data: props.data
    }]
  }
})

const chartOptions = {
  responsive: true
}
</script>

<style scoped>
.small-chart {
  width: 600px !important; /* Set your desired width */
  height: 400px !important; /* Set your desired height */
}
</style>
