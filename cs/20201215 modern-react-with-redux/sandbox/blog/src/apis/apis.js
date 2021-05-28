import axios from 'axios'

export const jsonplaceholder = axios.create({
    baseURL: 'https://jsonplaceholder.typicode.com'
})