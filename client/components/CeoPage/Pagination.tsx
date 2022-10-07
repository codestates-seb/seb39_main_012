import {colors} from '@/styles/colors'
import {useMemo} from 'react'
import styled from 'styled-components'

interface Props {
  total: number
  limit: number
  page: number
  setPage: React.Dispatch<React.SetStateAction<number>>
}

function Pagination({total, limit, page, setPage}: Props) {
  const numPages = Math.ceil(total / limit)

  const pagesArr = useMemo(() => {
    const pagesArr = []
    for (let i = 0; i < numPages; i++) {
      pagesArr.push(i + 1)
    }
    return pagesArr
  }, [numPages])

  return (
    <>
      <Nav>
        {pagesArr.map((number) => (
          <Button
            key={number}
            onClick={() => {
              console.log(page)
              setPage(number)
            }}
          >
            {number}
          </Button>
        ))}
      </Nav>
    </>
  )
}

const Nav = styled.nav`
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 4px;
  margin: 16px;
`

const Button = styled.button`
  border: none;
  border-radius: 8px;
  padding: 8px;
  margin: 0;
  color: black;
  font-size: 1rem;
  cursor: pointer;

  &:hover {
    color: white;
    background: ${colors.mainColor};
  }

  &[disabled] {
    background: grey;
    cursor: revert;
    transform: revert;
  }

  &[aria-current] {
    background: deeppink;
    font-weight: bold;
    cursor: revert;
    transform: revert;
  }
`

export default Pagination
