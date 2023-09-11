<template>
  <div class="ape">
    <h2 style="text-align: center;">Add New Lift</h2>
    <label>Lift:</label>
    <select v-model="selectedLiftType">
      <option v-for="lift in liftTypes" :key="lift">{{ lift }}</option>
    </select>
    <br>

    <label>Current:</label>
    <input type="number" v-model="currentLift" min="0" step="1">
    <select v-model="selectedCurrentLiftUnit">
      <option v-for="unit in unitOptions" :key="unit">{{ unit }}</option>
    </select>
    <label>Reps:</label>
    <input v-model="reps" type="range" min="1" max="10">
    <label>{{ reps }}</label>
    <br>

    <label>Goal 1RM:</label>
    <input type="number" v-model="goalLift" min="0" step="1">
    <select v-model="selectedGoalLiftUnit">
      <option v-for="unit in unitOptions" :key="unit">{{ unit }}</option>
    </select>
    <br>
    
    <button @click="emit('submitted', true)">Submit</button>
  </div>
</template>

<script setup lang="ts">
import { ref, Ref } from 'vue'

// eslint-disable-next-line
const emit = defineEmits(['submitted']) // compiler macro (not identified by eslint)

const liftTypes: Ref<string[]> = ref([
  'Squat',
  'Bench Press',
  'Deadlift',
  'Overhead Press',
  'Barbell Row'
])
const selectedLiftType: Ref<string> = ref('Squat')

const unitOptions: Ref<string[]> = ref(['lbs', 'kg'])
const selectedCurrentLiftUnit: Ref<string> = ref('lbs')
const selectedGoalLiftUnit: Ref<string> = ref('lbs')

const currentLift: Ref<string> = ref('')
const goalLift: Ref<string> = ref('')

const reps: Ref<string> = ref('1')
</script>

<style scoped>
.ape {
  border: 2px solid black;
  padding-left: 20px;
  padding-right: 20px;
  padding-bottom: 20px;
  width: 30%;
  margin: auto;
  text-align: left;
}
</style>
