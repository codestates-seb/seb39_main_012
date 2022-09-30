import {colors} from '@/styles/colors'
import {useState} from 'react'
import styled from 'styled-components'

const TapMenu = () => {
  const [currentTab, setCurrentTab] = useState(0)
  const menuArr = [{name: '견주님 로그인'}, {name: '사장님 로그인'}]

  const selectMenuHandler = (index: number) => {
    setCurrentTab(index)
  }

  return (
    <TabMenu>
      {menuArr.map((ele, index) => {
        return (
          <li
            key={index}
            className={currentTab === index ? 'submenu focused' : 'submenu'}
            onClick={() => selectMenuHandler(index)}
          >
            {ele.name}
          </li>
        )
      })}
    </TabMenu>
  )
}

export default TapMenu

const TabMenu = styled.ul`
  background-color: #fff;
  color: #9a9a9a;
  font-weight: bold;
  font-size: 1.5rem;
  display: flex;
  flex-direction: row;
  text-align: center;
  justify-content: space-between;
  align-items: center;
  list-style: none;
  margin: 2rem 0;
  border-bottom: 2px solid ${colors.grey1};

  .submenu {
    width: 100%;
    padding: 15px 10px;
    cursor: pointer;
  }

  .focused {
    border-bottom: 3px solid rgb(27, 27, 27);
    color: rgb(62, 62, 62);
  }
`
