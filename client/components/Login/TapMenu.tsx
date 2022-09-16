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
  font-size: 2rem;
  display: flex;
  flex-direction: row;
  text-align: center;
  justify-content: space-between;
  align-items: center;
  list-style: none;
  margin: 2rem 0;
  border-bottom: 2px solid ${colors.grey1};
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell,
    'Open Sans', 'Helvetica Neue', sans-serif;

  .submenu {
    width: 100%;
    padding: 15px 10px;
    cursor: pointer;
  }

  .focused {
    border-bottom: 3px solid #1b1b1b;
    color: #3e3e3e;
    transition: 0.3s;
  }
`
