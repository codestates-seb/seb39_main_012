import React from 'react'
import {Swiper, SwiperSlide} from 'swiper/react'
import Image, {StaticImageData} from 'next/image'
// Import Swiper styles
import 'swiper/css'
import 'swiper/css/pagination'
import 'swiper/css/navigation'
// import required modules
import {Autoplay, Pagination, Navigation} from 'swiper'

interface Props {
  banners: StaticImageData[]
}

function BannerSwiper({banners}: Props) {
  return (
    <>
      <Swiper
        spaceBetween={30}
        centeredSlides={true}
        autoplay={{
          delay: 2500,
          disableOnInteraction: false,
        }}
        pagination={{
          clickable: true,
        }}
        modules={[Autoplay, Pagination, Navigation]}
        className="mySwiper"
      >
        {banners.map((img, idx) => (
          <SwiperSlide key={idx}>
            <Image src={img} sizes={'100%'} alt={'BannerImg'} />
          </SwiperSlide>
        ))}
      </Swiper>
    </>
  )
}

export default BannerSwiper
