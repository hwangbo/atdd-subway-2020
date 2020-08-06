import ApiService from '@/api'

const PathService = {
  get(sourceId, targetId) {
    return ApiService.get(`/paths?source=${sourceId}&target=${targetId}&type=DISTANCE`)
  }
}

export default PathService
