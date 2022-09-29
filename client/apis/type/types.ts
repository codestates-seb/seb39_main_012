export interface ILogin {
  email: FormDataEntryValue | null
  password: FormDataEntryValue | null
}

export interface ICustomerSignUp {
  email: string
  password: string
  username: string
  phone: string
}

export interface ICompanySingUp {
  email: string
  password: string
  username: string
  phone: string
  ceo: string
  companyName: string
  address: string
  detailAddress: string
}

export interface IPostWrite {
  title: string
  content: string
  coordinate: string[]
  checkIn: string
  checkOut: string
  hashTag: string[]
  serviceTag: string[]
  roomCount: number
  roomDtoList: {
    size: string
    price: number
  }[]
}
