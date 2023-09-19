<!-- <template>
  <v-app>
    <v-main>
      <HelloWorld />
    </v-main>
  </v-app>
</template>

<script setup lang="ts">
  import HelloWorld from '@/components/HelloWorld.vue'
</script> -->
<template>
  <h1>Gym Bro</h1>
  <nav>
    <router-link to="/">My Lifts</router-link> |
    <router-link to="/about">Feed</router-link>
  </nav>
  <router-view/>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
.v-card {
  color: #f8f8f2 !important;
  background-color: #44475a !important;
}
.v-list {
  color: #f8f8f2 !important;
  background-color: #44475a !important;
}
.v-btn {
  background-color: #ff5555 !important;
}
</style>
