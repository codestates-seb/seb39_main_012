import {useState, useEffect} from 'react'

export function useScreenX() {
  const [screenX, setScreenX] = useState<number>(0)

  const listener = () => {
    setScreenX(window.innerWidth)
  }

  useEffect(() => {
    window.addEventListener('resize', listener)
    return () => {
      window.removeEventListener('resize', listener)
    }
  })

  return {
    screenX,
  }
}
