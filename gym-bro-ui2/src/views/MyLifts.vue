<template>
  <v-card class="my-lifts" title="My Lifts" style="float:left;">
    <v-list :items="lifts"></v-list>

<!-- 
    <li v-for="lift in lifts" :key="lift">
      {{ lift }}
    </li> -->
    <br>
    <v-btn @click="addNewLift" class="add-new-lift-btn" color="#ff5555">Create new lift</v-btn>
    <div id="plot-container" class="plot-container"></div>
  </v-card>

  <v-card class="chart-container">
    <ChartShower :data="chartData"/>
  </v-card>

  <AddNewLift v-model="showAddNewLift" @submitted="showAddNewLift=false" @progressPlan="postProgressPlanToChart($event)"/>
  
</template>

<script setup lang="ts">
import { ref, Ref } from 'vue'
import AddNewLift from '@/components/AddNewLift.vue'
import ChartShower from '@/components/ChartShower.vue'

// should get user lifts here from DB
const lifts: Ref<object[]> = ref([
  {
    title: 'Squat',
    value: 'squat'
  },
  {
    title: 'Barbell Row',
    value: 'barbell_row'
  }
])

const showAddNewLift: Ref<boolean> = ref(false)
function addNewLift () {
  showAddNewLift.value = true
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
  width: 20%;
  margin-left: 10%;
}
.chart-container {
  padding: 0px 50px;
  text-align: left;
  width: 58%;
  margin-left: 2%;
  margin-right: 10%;
  float:right;
}
.add-new-lift-btn {
  margin-left: 10px;
  margin-bottom: 10px;
}
</style>
