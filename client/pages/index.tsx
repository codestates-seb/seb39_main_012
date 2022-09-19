import Image from 'next/image'
import {ToastContainer} from 'react-toastify'
import styled from 'styled-components'
import mainBanner from '@/public/images/mainBanner.png'
import CategoryTag from '@/components/Home/CategoryTag'
import {colors} from '@/styles/colors'
import {categoryTags} from '@/utils/options/options'

function Home() {
  return (
    <Container>
      <MainBanner>
        <Image src={mainBanner} />
      </MainBanner>
      <MainSearchBar></MainSearchBar>
      <CategoryBox>
        {categoryTags.map((title) => (
          <CategoryTag title={title} />
        ))}
      </CategoryBox>
      <ToastContainer />
    </Container>
  )
}

export default Home

const Container = styled.div`
  width: 100%;
`

const MainBanner = styled.div`
  margin-bottom: 40px;
`

const MainSearchBar = styled.div`
  width: 100%;
  height: 150px;
  background-color: ${colors.grey1};

  margin-bottom: 40px;
`

const CategoryBox = styled.div`
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
  gap: 30px;
  width: 100%;
  height: 107px;

  & > div:nth-child(odd) {
    background-color: ${colors.subColor1};
    color: ${colors.subFontColor1};
  }
  & > div:nth-child(even) {
    background-color: ${colors.subColor2};
    color: ${colors.subFontColor2};
  }

  @media (max-width: 768px) {
    gap: 20px;
  }

  @media (max-width: 390px) {
    gap: 15px;
  }
`
