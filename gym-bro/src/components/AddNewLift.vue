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
    <select>
      <option v-for="unit in unitOptions" :key="unit">{{ unit }}</option>
    </select>
    <br>
    <label>Goal:</label>
    <input type="number" v-model="goalLift" min="0" step="1">
    <select>
      <option v-for="unit in unitOptions" :key="unit">{{ unit }}</option>
    </select>
    <br>
    <button @click="emit('submitted', true)">Submit</button>
  </div>
</template>

<script setup lang="ts">
import { ref, Ref, defineEmits } from 'vue'

const emit = defineEmits(['submitted'])

const liftTypes: Ref<string[]> = ref([
  'Squat',
  'Bench Press',
  'Deadlift',
  'Overhead Press',
  'Barbell Row'
])
const selectedLiftType: Ref<string> = ref('Squat')

const unitOptions: Ref<string[]> = ref(['lbs', 'kg'])

const currentLift: Ref<string> = ref('')
const goalLift: Ref<string> = ref('')
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
