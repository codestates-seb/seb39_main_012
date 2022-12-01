interface reservationListProps {
  id: null
  companyId: number
  reservationDate: string
  dogCount: number
}

interface mapProps {
  small: number
  medium: number
  big: number
}

export interface booking {
  reservationList: reservationListProps[]
  dto: {
    map: mapProps
    checkInDate: string
    checkOutDate: string
    checkInTime: string
    checkOutTime: string
  }
  checkInDate: string
  checkOutDate: string
  checkInTime: string
  checkOutTime: string
  totalDogCount: number
  totalPrice: number
  companyName: string
}

export interface ConfirmBooking {
  data: ConfirmBook[]
}

export interface ConfirmBook {
  photoImgUrl: string
  dogName: string
  type: string
  gender: string
  age: number
  weight: number
  snackMethod: string
  bark: string
  surgery: string
  bowelTrained: string
  etc: string
  phone: string
  name: string
  address: string
  checkInDate: Date
  checkOutDate: Date
  checkInTime: string
  checkOutTime: string
  totalPrice: number
}
