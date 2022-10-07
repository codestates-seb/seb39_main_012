import {FC, useEffect} from 'react'
import styled from 'styled-components'

interface Props {
  address: string
  companyName: string
}

const Map = ({address, companyName}: Props) => {
  useEffect(() => {
    const onLoadKakaoMap = () => {
      window.kakao.maps.load(() => {
        const geocoder = new window.kakao.maps.services.Geocoder()
        geocoder.addressSearch(address, (result: any, status: any) => {
          if (status === window.kakao.maps.services.Status.OK) {
            const coords = new window.kakao.maps.LatLng(result[0].y, result[0].x)
            const container = document.getElementById('map')!
            const options = {
              center: coords,
              level: 3,
            }
            const map = new window.kakao.maps.Map(container, options)
            const marker = new window.kakao.maps.Marker({
              map: map,
              position: coords,
            })
            const infowindow = new kakao.maps.InfoWindow({
              content: `<div style="width:150px;text-align:center;padding:6px 0;">${companyName}</div>`,
            })
            infowindow.open(map, marker)
          } else {
            const coords = new window.kakao.maps.LatLng(result[0].y, result[0].x)
            const container = document.getElementById('map')!
            const options = {
              center: new window.kakao.maps.LatLng(33.450701, 126.570667),
              level: 3,
            }
            const map = new window.kakao.maps.Map(container, options)
            new window.kakao.maps.Marker({
              map: map,
              position: coords,
            })
          }
        })
      })
    }
    onLoadKakaoMap()
  }, [address, companyName])

  return <MapContainer id="map" />
}

export default Map

const MapContainer = styled.div`
  width: 100%;
  height: 400px;
  border-radius: 1rem;
`
