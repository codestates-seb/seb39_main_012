import PostCard from '@/components/Home/PostCard'
import SearchBar from '@/components/Home/SearchBar'
import {colors} from '@/styles/colors'
import React, {useEffect, useState} from 'react'
import styled from 'styled-components'
import {postService} from '@/apis/PostAndSearchAPI'
import {Post} from '@/types/post'
import {useRouter} from 'next/router'
import LoadingSpinner from '@/components/LoadingSpinner/LoadingSpinner'
import useIntersect from '@/hooks/useIntersect'
import {flexCenter} from '@/styles/css'
import CategoryTag from '@/components/Home/CategoryTag'
import {categoryTags} from '@/utils/options/options'
import Image from 'next/image'
import NoSearchImg from '@/public/images/NoSearch.png'

function Search() {
  const router = useRouter()
  const [posts, setPosts] = useState<Post[]>([])
  const [page, setPage] = useState(2)
  const [totalPage, setTotalPage] = useState<number>(1)
  const [isLoading, setIsLoading] = useState(false)

  useEffect(() => {
    if (router.query.title) {
      const title = router.query.title as string
      postService.getPostsTitle(1, title).then((res) => {
        setPosts(res.data)
        setTotalPage(res.pageInfo.totalPages)
      })
    } else if (router.query.tag) {
      const tag = router.query.tag as string
      postService.getPostsTag(1, tag).then((res) => {
        setPosts(res.data)
        setTotalPage(res.pageInfo.totalPages)
      })
    } else if (router.query.address) {
      const address = router.query.address as string
      postService.getPostsAddress(1, address).then((res) => {
        setPosts(res.data)
        setTotalPage(res.pageInfo.totalPages)
      })
    }
  }, [router.query])

  const fetchMore = async () => {
    let data, res
    setIsLoading(true)
    if (router.query.title) {
      data = router.query.title as string
      res = await postService.getPostsTitle(page, data)
    } else if (router.query.tag) {
      data = router.query.tag as string
      res = await postService.getPostsTag(page, data)
    } else if (router.query.address) {
      data = router.query.address as string
      res = await postService.getPostsAddress(page, data)
    }
    if (res !== undefined) {
      setPosts([...posts, ...res.data])
      setPage(page + 1)
      setTotalPage(res.pageInfo.totalPages)
      setIsLoading(false)
    }
  }

  const callback = async () => {
    if (page > totalPage) return
    fetchMore()
  }

  const ref = useIntersect(callback)

  if (!posts) return <div>검색 결과가 없습니다.</div>

  return (
    <Container>
      <MainSearchBar>
        <SearchBar />
      </MainSearchBar>
      <CategoryBox>
        {categoryTags.map((title, idx) => (
          <CategoryTag title={title} key={idx} />
        ))}
      </CategoryBox>
      <PostCardBox>
        {posts.length === 0 ? (
          <NoContent>
            <Image src={NoSearchImg} alt="NoSearchImg" />
          </NoContent>
        ) : (
          posts.map((post: Post) => <PostCard post={post} key={post.id} />)
        )}
      </PostCardBox>
      {page > totalPage ? (
        <div></div>
      ) : isLoading ? (
        <SpinnerBox>
          <LoadingSpinner />
        </SpinnerBox>
      ) : (
        <Intersection ref={ref}></Intersection>
      )}
    </Container>
  )
}

export default Search

const Container = styled.div`
  width: 100%;
`

const MainSearchBar = styled.div`
  width: 100%;
  height: 100px;
  padding-top: 2rem;
  padding-bottom: 2rem;
  background-color: ${colors.grey1};
  margin-bottom: 20px;
`

const CategoryBox = styled.div`
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
  gap: 30px;
  width: 100%;
  margin: 40px 0;

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
const PostCardBox = styled.div`
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  gap: 15px;
`
const SpinnerBox = styled.div`
  ${flexCenter}
  width: 100%;
`

const Intersection = styled.div`
  height: 5px;
  width: 100%;
`

const NoContent = styled.div`
  ${flexCenter}
  width: 100%;
`
