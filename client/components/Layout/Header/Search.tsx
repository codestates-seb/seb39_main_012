import {colors} from '@/styles/colors'
import React, {useState} from 'react'
import styled from 'styled-components'
import {BsSearch} from 'react-icons/bs'
function Search() {
  const [serachValue, setSearchValue] = useState('')
  return (
    <Container>
      <SearchInput onChange={(e) => setSearchValue(e.target.value)}></SearchInput>
      <SerachIconBox>
        <BsSearch />
      </SerachIconBox>
    </Container>
  )
}

export default Search

const Container = styled.div`
  position: relative;
  flex: 0.6;
  height: 50px;
  @media (max-width: 768px) {
    display: none;
  }
`

const SearchInput = styled.input`
  width: 100%;
  height: 100%;
  border-radius: 10rem;
  padding: 0rem 2rem;
  font-size: 1.5rem;
  border: 1px solid rgba(0, 0, 0, 0.26);

  :focus {
    border: 0.05rem solid ${colors.mainColor};
    outline: 0.4rem solid rgb(231, 245, 232);
  }
`

const SerachIconBox = styled.div`
  position: absolute;
  top: 15px;
  right: 0;
  font-size: 2rem;
  color: #7d7d7d;
`
