package com.example.unsplashphotoviewer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso


class ListFragment : Fragment(), IListFragmentListener, IDataLoaderListener {

    private lateinit var linearLayout: LinearLayout
    private lateinit var startButton: Button
    private lateinit var dataLoader: IDataLoader
    private lateinit var listPhoto: List<Photo>
    private var listeners: List<IPhotoClickListener> = ArrayList()

    companion object {
         var mainListFragment: ListFragment? = null
    }

    fun addListener(newListener : IPhotoClickListener)
    {
            mainListFragment!!.listeners += newListener
    }
    fun removeListener(oldListener : IPhotoClickListener)
    {
        if(mainListFragment!!.listeners.contains(oldListener))
            mainListFragment!!.listeners -= oldListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(mainListFragment == null) mainListFragment = this
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        linearLayout = view.findViewById<LinearLayout>(R.id.listLayout);
        startButton = view.findViewById<Button>(R.id.startButton);
        dataLoader = UnsplashLoader()
        dataLoader.addListener(this)

       return view;
    }

    private fun DrawListItem(url: String, name: String, likes: Int, id: String)
    {
        //Главная плашка
        val newImageLayout = LinearLayout(context)
        newImageLayout.layoutParams =
            FrameLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT)
        newImageLayout.orientation = LinearLayout.HORIZONTAL

        //Картинка
        val imageView = ImageView(context)
        imageView.scaleType = ImageView.ScaleType.FIT_CENTER
        imageView.setImageResource(R.drawable.please_wait)
        imageView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT,
            0.7f
        )
        imageView.transitionName = id
        imageView.setOnClickListener({onPhotoClick(imageView.transitionName)})
        newImageLayout.addView(imageView)

        //Слой с текстом
        val newTextLinearLayout = LinearLayout(context)
        newTextLinearLayout.layoutParams =
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                0.3f)
        newTextLinearLayout.orientation = LinearLayout.VERTICAL


        //Никнейм
        val nameTextView = TextView(context)
        nameTextView.text = "Username: $name"
        newTextLinearLayout.addView(nameTextView)

        //Кол-во лайков
        val authorTextView = TextView(context)
        authorTextView.text = "Likes: $likes"//заменить на название
        newTextLinearLayout.addView(authorTextView)

        newImageLayout.addView(newTextLinearLayout)

        //Обновление картинок из другого потока
        linearLayout.post(Runnable
        {
            Picasso.get().load(url).into(imageView);
            linearLayout.addView(newImageLayout)
        })

    }

    private fun onPhotoClick(id: String)
    {
        listPhoto.forEach()
        {
            if(it.id == id)
            {
                MainActivity.viewPager.currentItem = 1
                val url = it.urls.full
                listeners.forEach()
                {
                    it.onPhotoClick(url)
                }
                return
            }
        }
    }

    override fun onStartClicked() {
        dataLoader.getData()
      }

    override fun onReceiveNewData(photos: List<Photo>) {
        startButton.post(Runnable {
            startButton.text = "ReRoll"
        })
        linearLayout.post(Runnable {
        linearLayout.removeAllViews()
        })
        listPhoto = photos
        listPhoto.forEach()
        {

            DrawListItem(it.urls.small,
                it.user.username,
                it.likes,
                it.id)
        }
    }

    override fun onGetError(message: String) {
        println(message)
    }


}