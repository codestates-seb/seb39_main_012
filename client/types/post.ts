export interface Post {
  id: number
  title: string
  address: string
  avgScore: number
  likesCount: number
  img: Img
  minPrice: number
}

export interface PageInfo {
  page: number
  size: number
  totalElements: number
  totalPages: number
}

export interface Img {
  fileName: string
  url: string
}
