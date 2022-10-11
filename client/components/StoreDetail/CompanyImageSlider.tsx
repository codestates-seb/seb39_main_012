/* eslint-disable jsx-a11y/alt-text */
/* eslint-disable @next/next/no-img-element */
import React from 'react'
import LightGallery from 'lightgallery/react'
import styled from 'styled-components'
import lgZoom from 'lightgallery/plugins/zoom'
import lgThumbnail from 'lightgallery/plugins/thumbnail'
import lgShare from 'lightgallery/plugins/share'
import lgRotate from 'lightgallery/plugins/rotate'
import lgFullscreen from 'lightgallery/plugins/fullscreen'
import {PostById} from '@/types/post'

interface Props {
  postImg: PostById['postsImgList'] | undefined
  companyName: string | undefined
  companyAddress: string
}

const CompanyImageSlider = ({postImg, companyName, companyAddress}: Props) => {
  // eslint-disable-next-line @typescript-eslint/no-empty-function
  const onInit = () => {}

  return (
    <CompanyImages>
      <LightGallery
        onInit={onInit}
        plugins={[lgZoom, lgThumbnail, lgShare, lgRotate, lgFullscreen]}
        mode="lg-fade"
        licenseKey={process.env.NEXT_PUBLIC_LIGHT_GALLERY_KEY}
      >
        {postImg?.map((img, index) => {
          return (
            <a
              key={index}
              data-lg-size="480-475-480, 800-791-800, 1406-1390"
              className="gallery-item"
              data-src={img.url}
              data-responsive={img.fileName + ' 480, ' + img.fileName + ' 800'}
              data-sub-html={'<h4>' + companyName + '</h4>  <p>' + companyAddress + '</p>'}
            >
              <img className="img-responsive" src={img.url} />
            </a>
          )
        })}
      </LightGallery>
    </CompanyImages>
  )
}

export default CompanyImageSlider

const CompanyImages = styled.div`
  margin-top: 2rem;
  cursor: pointer;
  /* display: flex; */
  max-width: 128rem;

  .lg-react-element {
    display: grid;
    grid-gap: 10px;
    grid-template-areas:
      'photoOne photoTwo photoThree'
      'photoOne photoFour photoFive';
    grid-template-columns: 1fr 25% 25%;
  }

  .lg-react-element > a {
    height: 250px;
  }

  .lg-react-element > a:first-child {
    grid-area: photoOne;
    height: 510px !important;
  }

  img {
    width: 100%;
    height: 100%;
    object-fit: fill;
    border-radius: 10px;
  }
`
