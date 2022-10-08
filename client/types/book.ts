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
