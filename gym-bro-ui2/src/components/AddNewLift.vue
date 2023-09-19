<template>
  <div class="ape">
    <h2 style="text-align: center;">Add New Lift</h2>
    <label>Lift:</label>
    <select v-model="selectedLiftType">
      <option v-for="lift in liftTypes" :key="lift">{{ lift }}</option>
    </select>
    <br>

    <label>Units:</label>
    <select v-model="selectedUnit">
      <option v-for="unit in unitOptions" :key="unit">{{ unit }}</option>
    </select>
    <br>

    <label>Current:</label>
    <input type="number" v-model="currentLift" min="0" step="1">
    <label>Reps:</label>
    <input v-model="reps" type="range" min="1" max="10">
    <label>{{ reps }}</label>
    <br>

    <label>Estimated current 1RM: {{ currentOneRepMax }} {{ selectedUnit }}</label>
    <br>

    <label>Goal 1RM:</label>
    <input type="number" v-model="goalLift" min="0" step="1">
    <br>

    <label>Protocol type:</label>
    <select v-model="selectedProtocolType">
      <option v-for="protocol in protocolTypes" :key="protocol">{{ protocol }}</option>
    </select>
    <br>

    <button @click="submit">Submit</button>
  </div>
</template>

<script setup lang="ts">
import axios from 'axios'
import { computed, ref, Ref } from 'vue'

// eslint-disable-next-line
const emit = defineEmits(['submitted', 'progressPlan']) // compiler macro (not identified by eslint)

const progressPlan: Ref<Array<number>> = ref([])

function BrzyckiOneRepMax (weightLbs: number, numReps: number): number {
  if (numReps < 1 || numReps > 10) {
    throw new Error('numReps must be between 1 and 10')
  }
  return Math.floor(weightLbs / (1.0278 - 0.0278 * numReps)) // Brzycki Equation
}

function kgsToLbs (weightKgs: number): number {
  return Math.round(weightKgs * 2.20462)
}

function lbsToKgs (weightLbs: number): number {
  return Math.round(weightLbs / 2.20462)
}

// const currentOneRepMax: Ref<string> = ref('')
const currentOneRepMax: Ref<string> = computed(() => {
  // Reactive current one rep max
  let currentOneRepMax: string
  if (selectedUnit.value === 'kg') {
    currentOneRepMax = kgsToLbs(Number(currentLift.value)).toString() // convert to lbs for calculations
    currentOneRepMax = lbsToKgs(BrzyckiOneRepMax(kgsToLbs(Number(currentLift.value)), Number(reps.value))).toString()
  } else {
    currentOneRepMax = BrzyckiOneRepMax(Number(currentLift.value), Number(reps.value)).toString()
  }
  return currentOneRepMax
})

function submit () {
  console.log(selectedUnit.value)
  axios.post('http://localhost:5004/calcWeight', {
    currentOneRepMax: currentOneRepMax.value,
    goalOneRepMax: goalLift.value,
    units: selectedUnit.value,
    protocolType: selectedProtocolType.value
  })
    .then(response => {
      progressPlan.value = response.data
      console.log('this is running')
      emit('progressPlan', progressPlan.value)
      emit('submitted', true)
    })
    .catch(error => {
      console.log(error)
    })
}

const liftTypes: Ref<string[]> = ref([
  'Squat',
  'Bench Press',
  'Deadlift',
  'Overhead Press',
  'Barbell Row'
])
const selectedLiftType: Ref<string> = ref('Squat')

const unitOptions: Ref<string[]> = ref(['lbs', 'kg'])
const selectedUnit: Ref<string> = ref('lbs')

const currentLift: Ref<string> = ref('')
const goalLift: Ref<string> = ref('')

const reps: Ref<string> = ref('1')

const protocolTypes: Ref<string[]> = ref([
  'Conservative',
  'Aggressive'
])
const selectedProtocolType: Ref<string> = ref('Conservative')
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
