<template>
  <div class="my-lifts">
    <h1>My Lifts</h1>
    <li v-for="lift in lifts" :key="lift">
      {{ lift }}
    </li>
    <br>
    <button @click="addNewLift">Create new lift</button>
  </div>
  <AddNewLift v-show="showAddNewLift" @submitted="showAddNewLift=false"/>
</template>

<script setup lang="ts">
import { ref, Ref } from 'vue'
import AddNewLift from '@/components/AddNewLift.vue'

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
</script>

<style>
.my-lifts {
  padding: 0px 50px;
  text-align: left;
}
</style>
