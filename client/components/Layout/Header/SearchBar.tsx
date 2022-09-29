import React from 'react'
import styled from 'styled-components'
import {colors} from '@/styles/colors'
import {BsSearch} from 'react-icons/bs'
import {useRecoilState} from 'recoil'
import {titleSearchState} from '@/recoil/searchValue'
import {useRouter} from 'next/router'

const Search = () => {
  const [searchValue, setSearchValue] = useRecoilState(titleSearchState)
  const router = useRouter()
  return (
    <SearchContainer>
      <SearchInput
        placeholder="검색어를 입력해주세요."
        value={searchValue}
        onChange={(e) => setSearchValue(e.target.value)}
        onKeyDown={(e) => {
          if (e.key === 'Enter') {
            e.preventDefault()
            router.push(`/search?title=${searchValue}`)
          }
        }}
      />
      <BsSearch
        className="searchIcon"
        onClick={(e) => {
          e.preventDefault()
          router.push(`/search?title=${searchValue}`)
        }}
      />
    </SearchContainer>
  )
}

export default Search

const SearchContainer = styled.div`
  display: flex;
  align-items: center;
  padding: 0.5rem;

  .searchIcon {
    color: ${colors.grey4};
    font-size: 1.6rem;
    position: relative;
    float: right;
    top: 0px;
    right: 30px;
    cursor: pointer;
  }
`
const SearchInput = styled.input`
  width: 100%;
  height: 100%;
  border-radius: 10rem;
  padding: 1rem 2rem;
  font-size: 1.4rem;
  border: 1px solid rgba(0, 0, 0, 0.26);
  outline: none;

  ::placeholder {
    color: rgb(188, 188, 188);
    font-size: 1.3rem;
  }

  :focus {
    border: 0.05rem solid ${colors.mainColor};
    outline: 0.4rem solid rgb(231, 245, 232);
  }
`
