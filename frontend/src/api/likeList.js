import axios from 'axios'

export function getLikeList(userId) {
  return axios.get(`/like-list/${userId}`)
}

export function addLikeItem(data) {
  return axios.post('/like-list/add', data)
}

export function updateLikeItem(data) {
  return axios.put('/like-list/update', data)
}

export function deleteLikeItem(data) {
  return axios.delete('/like-list/delete', { data })
}