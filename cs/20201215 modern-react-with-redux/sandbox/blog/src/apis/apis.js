import axios from 'axios'

export const fetchedJson = axios.create({
    baseURL: 'https://jsonplaceholder.typicode.com'
})