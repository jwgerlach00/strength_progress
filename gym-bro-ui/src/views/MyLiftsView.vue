<template>
  <div class="my-lifts">
    <h1>My Lifts</h1>
    <li v-for="lift in lifts" :key="lift">
      {{ lift }}
    </li>
    <br>
    <button @click="addNewLift">Create new lift</button>
    <div id="plot-container" class="plot-container"></div>
  </div>
  <AddNewLift v-if="showAddNewLift" @submitted="showAddNewLift=false" @progressPlan="postProgressPlanToChart($event)"/>
  <ChartShower :data="chartData"/>
</template>

<script setup lang="ts">
import { ref, Ref } from 'vue'
import AddNewLift from '@/components/AddNewLift.vue'
import ChartShower from '@/components/ChartShower.vue'

// should get user lifts here from DB
const lifts: Ref<string[]> = ref([
  'Squat',
  'Barbell Row'
])

const showAddNewLift: Ref<boolean> = ref(false)
function addNewLift () {
  showAddNewLift.value = true
  console.log('Adding new lift')
  // open add new lift form
}

const chartData: Ref<Array<number>> = ref([])
function postProgressPlanToChart (progressPlan: Array<number>) {
  chartData.value = progressPlan
}
</script>

<style>
.my-lifts {
  padding: 0px 50px;
  text-align: left;
}
</style>
