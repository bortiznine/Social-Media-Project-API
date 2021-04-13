<template>
  <Posts :posts="posts" />
</template>

<script>
import Posts from "@/components/Posts";

export default {
  name: 'App',
  components: {
    Posts,
  },
  data() {
    return {
      posts: [],
    }
  },
  async created() {
    this.posts = await this.fetchPosts()
  },
  methods: {
    async fetchPosts() {
      const res = await fetch('http://localhost:9092/api/posts')
      return await res.json()
    },
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

h1 {
  font-size: 2.8em;
}

h2 {
  font-size: 2.2em;
}

h3 {
  font-size: 1.6em;
}
</style>
