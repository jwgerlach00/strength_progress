<template>
  <v-dialog v-model="show" @click:outside="emit('update:modelValue', false)">
    <v-card>
      <h2 style="text-align:center;padding-bottom:5%;padding-top:5%;">Add New Lift</h2>

      <v-row dense>
        <v-col cols="6">
          <v-select label="Lift type" :items="liftTypes" v-model="selectedLiftType" variant="outlined"/>
        </v-col>
        <v-col cols="6">
          <v-select label="Units" :items="unitOptions" v-model="selectedUnit" variant="outlined"/>
        </v-col>
      </v-row>

      <v-row dense>
        <v-col>
          <v-text-field label="Current weight" v-model="currentLift" variant="outlined"></v-text-field>
        </v-col>
        <v-col>
          <v-label>Reps: {{ reps }}</v-label>
          <v-slider v-model="reps" min="1" max="10" step="1" show-ticks="always" tick-size="4" color="white"/>
        </v-col>
      </v-row>
      <label style="color:#50fa7b;">Estimated current 1RM: {{ currentOneRepMax }} {{ selectedUnit }}</label>
      <br>


      <v-row dense>
        <v-col cols="6">
          <v-text-field label="Goal 1RM" v-model="goalLift" variant="outlined"></v-text-field>
        </v-col>
        <v-col cols="6">
          <v-select label="Protocol type" :items="protocolTypes" v-model="selectedProtocolType" variant="outlined"/>
        </v-col>
      </v-row>

      <v-row dense>
        <v-col cols="12">
          <v-btn @click="submit" style="width:100%;" color="#50fa7b">Submit</v-btn>
        </v-col>
      </v-row>

    </v-card>
  </v-dialog>
</template>

<script setup lang="ts">
import axios from 'axios'
import { computed, ref, Ref } from 'vue'

const props = defineProps({
  modelValue: {
    type: Boolean,
    default: false,
    required: true
  }
})

// eslint-disable-next-line
const emit = defineEmits(['submitted', 'progressPlan']) // compiler macro (not identified by eslint)

const show = computed(() => props.modelValue)

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
.v-card {
  padding-left: 20px;
  padding-right: 20px;
  padding-bottom: 20px;
  width: 30%;
  margin: auto;
  text-align: left;
}
</style>
