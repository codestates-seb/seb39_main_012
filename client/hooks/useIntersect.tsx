/* eslint-disable react-hooks/exhaustive-deps */
import {useEffect, useState} from 'react'

function useIntersect(callback: () => void) {
  const [lastIntersecting, setLastIntersecting] = useState<HTMLDivElement | null>(null)

  //observer 콜백함수
  const onIntersect: IntersectionObserverCallback = (entries, observer) => {
    entries.forEach((entry) => {
      if (entry.isIntersecting) {
        //뷰포트에 마지막 이미지가 들어오고, page값에 1을 더하여 새 fetch 요청을 보내게됨 (useEffect의 dependency배열에 page가 있음)
        callback()
        // 현재 타겟을 unobserve한다.
        observer.unobserve(entry.target)
      }
    })
  }

  useEffect(() => {
    //observer 인스턴스를 생성한 후 구독
    let observer: IntersectionObserver
    if (lastIntersecting) {
      observer = new IntersectionObserver(onIntersect, {threshold: 0.5})
      //observer 생성 시 observe할 target 요소는 불러온 이미지의 마지막아이템(randomImageList 배열의 마지막 아이템)으로 지정
      observer.observe(lastIntersecting)
    }
    return () => observer && observer.disconnect()
  }, [lastIntersecting])

  return setLastIntersecting
}

export default useIntersect
